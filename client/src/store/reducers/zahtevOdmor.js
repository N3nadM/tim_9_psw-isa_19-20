import { SET_ZAHTEV_ODMOR } from "../actionTypes";

const DEFAULT_STATE = {
  zahtevOdmor: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ZAHTEV_ODMOR:
      return {
        zahtevOdmor: action.zahtevOdmor
      };
    default:
      return state;
  }
};
