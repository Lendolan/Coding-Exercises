import { useNavigate } from "react-router-dom";

function App() {
  const navigate = useNavigate();
  return (
    <div className="steps">
      <button className="btn" onClick={() => navigate("/store-owner")}>
        Store Owner Login
      </button>
      <button className="btn" onClick={() => navigate("/pizza-chef")}>
        Pizza Chef Login
      </button>
    </div>
  );
}

export default App;
