import { combineReducers } from "redux";
import currentUser from "./currentUser";
import zahtev from "./zahtev";

const rootReducer = combineReducers({
  currentUser,
  zahtev
});

export default rootReducer;
