import { SET_ZAHTEV_ODMOR, SET_ZAHTEVI_ADMIN_ODMOR } from "../actionTypes";

const DEFAULT_STATE = {
  zahtevOdmor: null,
  listaZahtevaOdmor: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ZAHTEV_ODMOR:
      return {
        zahtevOdmor: action.zahtevOdmor
      };
    case SET_ZAHTEVI_ADMIN_ODMOR:
      return {
        listaZahtevaOdmor: action.listaZahtevaOdmor
      };
    default:
      return state;
  }
};
