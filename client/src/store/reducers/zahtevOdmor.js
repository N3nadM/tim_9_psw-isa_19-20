import {
  SET_ZAHTEV_ODMOR,
  SET_ZAHTEVI_ADMIN_ODMOR,
  SET_ODBIJEN_ZAHTEV_ODMOR,
  SET_PRIHVACEN_ZAHTEV_ODMOR
} from "../actionTypes";

const DEFAULT_STATE = {
  zahtevOdmor: null,
  listaZahtevaOdmor: null,
  prihvacenZahtevOdmor: null,
  odbijenZahtevOdmor: null
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
    case SET_PRIHVACEN_ZAHTEV_ODMOR:
      return {
        ...state,
        prihvacenZahtevOdmor: action.prihvacenZahtevOdmor,
        listaZahtevaOdmor: state.listaZahtevaOdmor.filter(
          zahtev => zahtev.id !== action.prihvacenZahtevOdmor.id
        )
      };
    case SET_ODBIJEN_ZAHTEV_ODMOR:
      return {
        ...state,
        odbijenZahtevOdmor: action.odbijenZahtevOdmor,
        listaZahtevaOdmor: state.listaZahtevaOdmor.filter(
          zahtev => zahtev.id !== action.odbijenZahtevOdmor.id
        )
      };
    default:
      return state;
  }
};
