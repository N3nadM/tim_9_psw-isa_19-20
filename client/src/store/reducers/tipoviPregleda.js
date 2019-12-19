import {
  SET_ALL_TIPOVI_PREGLEDA,
  SET_ADDED_TIP_PREGLEDA,
  SET_TIPOVI_KOJI_SE_MOGU_IZMENITI,
  SET_EDITED_TIP
} from "../actionTypes";

const DEFAULT_STATE = {
  tipoviPregleda: null,
  newTipPregleda: null,
  tipoviZaIzmenu: null,
  editedTip: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ALL_TIPOVI_PREGLEDA:
      return {
        ...state,
        tipoviPregleda: action.tipoviPregleda
      };
    case SET_ADDED_TIP_PREGLEDA:
      return {
        ...state,
        newTipPregleda: action.newTipPregleda
      };
    case SET_TIPOVI_KOJI_SE_MOGU_IZMENITI:
      return {
        ...state,
        tipoviZaIzmenu: action.tipoviZaIzmenu
      };
    case SET_EDITED_TIP:
      return {
        ...state,
        editedTip: action.editedTip,
        tipoviZaIzmenu: state.tipoviZaIzmenu.map(t =>
          t.id === action.editedTip.id ? action.editedTip : t
        )
      };
    default:
      return state;
  }
};
