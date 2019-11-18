import { combineReducers } from "redux";
import currentUser from "./currentUser";
import zahtev from "./zahtev";
import pacijent from "./pacijent";

const rootReducer = combineReducers({
  currentUser,
  zahtev,
  pacijent
});

export default rootReducer;
