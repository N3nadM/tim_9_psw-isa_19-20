import { combineReducers } from "redux";
import currentUser from "./currentUser";
import zahtev from "./zahtev";
import pacijent from "./pacijent";
import klinika from "./klinika";
import zdrKarton from "./zdrKarton";

const rootReducer = combineReducers({
  currentUser,
  zahtev,
  pacijent,
  klinika,
  zdrKarton
});

export default rootReducer;
