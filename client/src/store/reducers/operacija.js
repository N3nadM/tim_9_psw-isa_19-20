import {
  SET_OPERACIJE,
  SET_OPERACIJE_ZA_SALU,
  SET_OPERACIJE_LEKAR,
  SET_OPERACIJE_SESTRA,
  SET_OPERACIJE_LEKAR_ODMOR,
  SET_OPERACIJE_SESTRA_ODMOR
} from "../actionTypes";

const DEFAULT_STATE = {
  operacije: [],
  operacijeZaSalu: [],
  operacijeKodLekara: [],
  operacijeKodSestre: [],
  operacijeKodLekaraOdmor: [],
  operacijeKodSestreOdmor: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_OPERACIJE:
      return {
        operacije: action.operacije.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_OPERACIJE_ZA_SALU:
      return {
        operacijeZaSalu: action.operacijeZaSalu.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    case SET_OPERACIJE_SESTRA:
      return {
        ...state,
        operacijeKodSestre: action.operacijeKodSestre
      };
    case SET_OPERACIJE_LEKAR:
      return {
        ...state,
        operacijeKodLekara: action.operacijeKodLekara
      };
    case SET_OPERACIJE_SESTRA_ODMOR:
      return {
        ...state,
        operacijeKodSestreOdmor: action.operacijeKodSestreOdmor
      };
    case SET_OPERACIJE_LEKAR_ODMOR:
      return {
        ...state,
        operacijeKodLekaraOdmor: action.operacijeKodLekaraOdmor
      };
    default:
      return state;
  }
};
