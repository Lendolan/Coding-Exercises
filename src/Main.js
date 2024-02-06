import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";
import StoreOwner from "./StoreOwner";
import PizzaChef from "./PizzaChef";

function Main() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/store-owner" element={<StoreOwner />} />
        <Route path="/pizza-chef" element={<PizzaChef />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Main;
