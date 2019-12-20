import { SET_ODMOR } from "../actionTypes";
import axios from "axios";

export const setOdmor = odmor => ({
  type: SET_ODMOR,
  odmor
});

export const addNewOdmor = data => async (dispatch, getState) => {
  try {
    const odmor = await axios.post(`/api/odmor`, data);
    dispatch(setOdmor(odmor.data));
  } catch (err) {
    console.log(err);
  }
};
