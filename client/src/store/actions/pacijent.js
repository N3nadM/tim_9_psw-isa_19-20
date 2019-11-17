import { SET_PACIJENT_PROFILE } from "../actionTypes";
import axios from "axios";

export const setProfile = pacijent => ({
  type: SET_PACIJENT_PROFILE,
  pacijent
});

export const getPacijent = id => async (dispatch, getState) => {
  try {
    const pacijent = await axios.get(`/api/pacijent/${id}`);
    dispatch(setProfile(pacijent.data));
  } catch (err) {
    console.log(err);
  }
};
