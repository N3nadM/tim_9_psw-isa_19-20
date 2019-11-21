import { combineReducers } from "redux";
import currentUser from "./currentUser";
import zahtev from "./zahtev";
import pacijent from "./pacijent";
import klinika from "./klinika";

const rootReducer = combineReducers({
  currentUser,
  zahtev,
  pacijent,
  klinika
});

export default rootReducer;
