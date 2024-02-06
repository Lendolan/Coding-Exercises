import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function StoreOwner() {
  const [toppings, setToppings] = useState([]);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchToppings = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/toppings");
        setToppings(response.data);
      } catch (error) {
        console.error("There was an error fetching the toppings:", error);
        setError("Could not fetch toppings.");
      }
    };

    fetchToppings();
  }, []);

  const addTopping = async (toppingName) => {
    try {
      const newTopping = { name: toppingName };
      const response = await axios.post(
        "http://localhost:8080/api/toppings",
        newTopping
      );
      setToppings([...toppings, response.data]);
      setError("");
    } catch (error) {
      console.error("There was an error adding the topping:", error);
      setError("Could not add topping.");
    }
  };

  const removeTopping = async (toppingId) => {
    try {
      await axios.delete(`http://localhost:8080/api/toppings/${toppingId}`);
      setToppings(toppings.filter((topping) => topping.id !== toppingId));
      setError("");
    } catch (error) {
      console.error("There was an error removing the topping:", error);
      setError("Could not remove topping.");
    }
  };

  const updateTopping = async (toppingId, newName) => {
    try {
      const updatedTopping = { name: newName };
      const response = await axios.put(
        `http://localhost:8080/api/toppings/${toppingId}`,
        updatedTopping
      );
      setToppings(
        toppings.map((topping) =>
          topping.id === toppingId ? response.data : topping
        )
      );
      setError("");
    } catch (error) {
      console.error("There was an error updating the topping:", error);
      setError("Could not update topping.");
    }
  };

  return (
    <div>
      <Header />
      <Toppings
        toppings={toppings}
        onAddTopping={addTopping}
        onRemoveTopping={removeTopping}
        onUpdateTopping={updateTopping}
        error={error}
      />
      <div
        style={{ display: "flex", justifyContent: "center", marginTop: "20px" }}
      >
        <button className="btn" onClick={() => navigate("/")}>
          Return to Home
        </button>
      </div>
    </div>
  );
}

function Header() {
  return (
    <header className="header">
      <h1>Pizza Topping Manager</h1>
    </header>
  );
}

function Toppings({
  toppings,
  onAddTopping,
  onRemoveTopping,
  onUpdateTopping,
  error,
}) {
  const [newToppingName, setNewToppingName] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!newToppingName.trim()) return;
    onAddTopping(newToppingName);
    setNewToppingName("");
  };

  return (
    <main className="toppings">
      <h2>Current toppings available for the pizza chefs</h2>
      {toppings.length > 0 ? (
        <>
          <ul>
            {toppings.map((topping, index) => (
              <Topping
                toppingObj={topping}
                key={index}
                onRemoveTopping={onRemoveTopping}
                onUpdateTopping={onUpdateTopping}
              />
            ))}
          </ul>
        </>
      ) : (
        <p>There are currently no toppings available!</p>
      )}
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={newToppingName}
          onChange={(e) => setNewToppingName(e.target.value)}
          placeholder="Enter new topping"
        />
        <button className="smlbtn" type="submit">
          Add Topping
        </button>
      </form>
    </main>
  );
}

function Topping({ toppingObj, onRemoveTopping, onUpdateTopping }) {
  const [isEditing, setIsEditing] = useState(false);
  const [editedName, setEditedName] = useState(toppingObj.name);

  const handleEdit = () => {
    if (isEditing) {
      onUpdateTopping(toppingObj.id, editedName);
    }
    setIsEditing(!isEditing);
  };

  return (
    <li>
      <div className="topping-item">
        {isEditing ? (
          <input
            type="text"
            value={editedName}
            onChange={(e) => setEditedName(e.target.value)}
          />
        ) : (
          <>
            <button
              onClick={handleEdit}
              style={{
                border: "none",
                background: "transparent",
                cursor: "pointer",
              }}
            >
              ✏️
            </button>
            <h3>{toppingObj.name}</h3>
          </>
        )}
        {!isEditing && (
          <button
            onClick={() => onRemoveTopping(toppingObj.id)}
            style={{
              border: "none",
              background: "transparent",
              cursor: "pointer",
            }}
          >
            🗑️
          </button>
        )}
      </div>
      {isEditing && (
        <button
          className="smlbtn"
          onClick={handleEdit}
          style={{
            border: "none",
            background: "transparent",
            cursor: "pointer",
          }}
        >
          Save
        </button>
      )}
    </li>
  );
}

export default StoreOwner;
