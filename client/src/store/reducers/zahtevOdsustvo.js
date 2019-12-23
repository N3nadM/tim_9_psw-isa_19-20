import { SET_ZAHTEV_ODSUSTVO } from "../actionTypes";

const DEFAULT_STATE = {
  zahtevOdsustvo: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ZAHTEV_ODSUSTVO:
      return {
        zahtevOdsustvo: action.zahtevOdsustvo
      };
    default:
      return state;
  }
};
