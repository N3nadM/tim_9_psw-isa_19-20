import { SET_PACIJENT_PROFILE } from "../actionTypes";

const DEFAULT_STATE = {
  pacijent: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_PACIJENT_PROFILE:
      return {
        pacijent: action.pacijent
      };

    default:
      return state;
  }
};
