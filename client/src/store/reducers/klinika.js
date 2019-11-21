import { SET_ALL_KLINIKE } from "../actionTypes";

const DEFAULT_STATE = {
  klinike: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ALL_KLINIKE:
      return {
        klinike: action.klinike
      };
    default:
      return state;
  }
};
