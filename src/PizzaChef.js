import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function PizzaChef() {
  const [pizzas, setPizzas] = useState([]);
  const [toppings, setToppings] = useState([]);
  const [newPizzaName, setNewPizzaName] = useState("");
  const [selectedToppings, setSelectedToppings] = useState([]);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const fetchData = async () => {
    try {
      const [pizzasRes, toppingsRes] = await Promise.all([
        axios.get("http://localhost:8080/api/pizzas"),
        axios.get("http://localhost:8080/api/toppings"),
      ]);
      setPizzas(pizzasRes.data);
      setToppings(toppingsRes.data);
    } catch (error) {
      console.error("There was an error fetching data:", error);
      setError("Could not fetch data.");
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleToppingChange = (toppingId) => {
    const newSelection = selectedToppings.includes(toppingId)
      ? selectedToppings.filter((id) => id !== toppingId)
      : [...selectedToppings, toppingId];
    setSelectedToppings(newSelection);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!selectedToppings.length) {
      setError("Please select at least one topping for the pizza.");
      return; // Prevent submission without toppings
    }

    try {
      // Adjusting payload to match the new DTO structure expected by the backend
      const payload = {
        name: newPizzaName,
        toppingIds: selectedToppings, // Directly sending the array of selected topping IDs
      };

      await axios.post("http://localhost:8080/api/pizzas", payload);

      // Resetting the form and re-fetching pizzas to reflect the new addition
      setNewPizzaName("");
      setSelectedToppings([]);
      setError("");
      fetchData();
    } catch (error) {
      console.error(
        "There was an error adding the pizza:",
        error.response.data
      );
      setError("Could not add pizza. " + error.response.data);
    }
  };

  const renderPizzas = () => {
    console.log(pizzas);
    return pizzas.map((pizza) => (
      <div key={pizza.id} style={{ margin: "10px 0" }}>
        <h3>{pizza.name}</h3>
        <ul>
          {pizza.toppings?.map((topping) => (
            <li key={topping.id}>{topping.name}</li>
          ))}
        </ul>
      </div>
    ));
  };

  return (
    <div>
      <Header title="Pizza Manager" />
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={newPizzaName}
          onChange={(e) => setNewPizzaName(e.target.value)}
          placeholder="Pizza Name"
        />
        <select
          multiple={true}
          value={selectedToppings}
          onChange={(e) => handleToppingChange(e.target.value)}
        >
          {toppings.map((topping) => (
            <option key={topping.id} value={topping.id}>
              {topping.name}
            </option>
          ))}
        </select>
        <button type="submit">Add Pizza</button>
      </form>
      {error && <div>{error}</div>}
      <div
        style={{ display: "flex", justifyContent: "center", marginTop: "20px" }}
      >
        <button className="btn" onClick={() => navigate("/")}>
          Return to Home
        </button>
      </div>
      <div>
        <h2>Available Pizzas</h2>
        {renderPizzas()}
      </div>
    </div>
  );
}

function Header() {
  return (
    <header className="header">
      <h1>Pizza Manager</h1>
    </header>
  );
}

export default PizzaChef;
