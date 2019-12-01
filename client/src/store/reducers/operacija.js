import { SET_OPERACIJE } from "../actionTypes";

const DEFAULT_STATE = {
  operacije: []
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_OPERACIJE:
      return {
        operacije: action.operacije.sort(
          (a, b) => new Date(b.datum) - new Date(a.datum)
        )
      };
    default:
      return state;
  }
};
