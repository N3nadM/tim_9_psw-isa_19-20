import { SET_ALL_ADMINKC, SET_ADDED_ADMINKC } from "../actionTypes";

const DEFAULT_STATE = {
  admins: null,
  newAdmin: null
};

export default (state = DEFAULT_STATE, action) => {
  switch (action.type) {
    case SET_ALL_ADMINKC:
      return {
        ...state,
        admins: action.admins
      };
    case SET_ADDED_ADMINKC:
      return {
        ...state,
        newAdmin: action.newAdmin
      };
    default:
      return state;
  }
};
