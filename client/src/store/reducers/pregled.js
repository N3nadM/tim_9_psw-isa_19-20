import { SET_PREGLEDI } from "../actionTypes";

const DEFAULT_STATE = {
  pregledi: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_PREGLEDI:
      return {
        pregledi: action.pregledi.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    default:
      return state;
  }
};
