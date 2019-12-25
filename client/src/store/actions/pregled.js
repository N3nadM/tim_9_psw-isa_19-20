import {
  SET_PREGLEDI,
  SET_PREGLEDI_ZA_SALU,
  SET_LISTA_PREDEFINISANIH_PREGLEDA,
  SET_FILTERED_PREGLEDI,
  SET_FILTERED_PREDEFINISANI,
  SET_PREGLEDI_LEKAR,
  SET_PREGLEDI_SESTRA,
  SET_PREGLEDI_SESTRA_ODMOR,
  SET_PREGLEDI_LEKAR_ODMOR
} from "../actionTypes";
import axios from "axios";

export const setPregledi = pregledi => ({
  type: SET_PREGLEDI,
  pregledi
});

export const setPreglediZaSalu = preglediZaSalu => ({
  type: SET_PREGLEDI_ZA_SALU,
  preglediZaSalu
});

export const setListaPredefinisanih = listaPredefinisanih => ({
  type: SET_LISTA_PREDEFINISANIH_PREGLEDA,
  listaPredefinisanih
});

export const filterPreglediAfterDelete = id => ({
  type: SET_FILTERED_PREGLEDI,
  id
});

export const setFilteredPredefinisani = id => ({
  type: SET_FILTERED_PREDEFINISANI,
  id
});
export const setPreglediKodLekara = preglediKodLekara => ({
  type: SET_PREGLEDI_LEKAR,
  preglediKodLekara
});
export const setPreglediKodSestre = preglediKodSestre => ({
  type: SET_PREGLEDI_SESTRA,
  preglediKodSestre
});

export const setPreglediKodLekaraOdmor = preglediKodLekaraOdmor => ({
  type: SET_PREGLEDI_LEKAR_ODMOR,
  preglediKodLekaraOdmor
});
export const setPreglediKodSestreOdmor = preglediKodSestreOdmor => ({
  type: SET_PREGLEDI_SESTRA_ODMOR,
  preglediKodSestreOdmor
});

export const getAllPregledi = id => async (dispatch, getState) => {
  try {
    let pregledi = getState().pregled.pregledi;
    if (!pregledi.length) {
      pregledi = await axios.get(`/api/pregled/${id}`);
      dispatch(setPregledi(pregledi.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const getAllOsobljePregledi = id => async (dispatch, getState) => {
  try {
    let pregledi = getState().pregled.pregledi;
    if (!pregledi.length) {
      pregledi = await axios.get(`/api/pregled/osoblje/${id}`);
      dispatch(setPregledi(pregledi.data));
    }
  } catch (err) {
    console.log(err);
  }
};

export const getAllSalaPregledi = id => async dispatch => {
  try {
    let preglediZaSalu = await axios.get(`/api/pregled/sala/${id}`);
    dispatch(setPreglediZaSalu(preglediZaSalu.data));
  } catch (err) {
    console.log(err);
  }
};

export const setPregled = data => async () => {
  try {
    await axios.post(`/api/pregled`, data);
  } catch (err) {
    console.log(err);
  }
};

export const getPredefinisaniPregledi = id => async dispatch => {
  try {
    let pregledi = await axios.get(`/api/pregled/predefinisani/${id}`);
    dispatch(setListaPredefinisanih(pregledi.data));
  } catch (err) {
    console.log(err);
  }
};

export const deletePregled = id => async dispatch => {
  try {
    const res = await axios.post("/api/pregled/otkaziPregled", {
      pregledId: id
    });
    if (res.data) {
      dispatch(filterPreglediAfterDelete(id));
    }
  } catch (err) {
    console.log(err);
  }
};

export const rezervisiPregled = id => async dispatch => {
  try {
    const res = await axios.post("/api/pregled/zakaziPredefinisani", {
      pregledId: id
    });
    if (res.data) {
      console.log("uspesno");
    }
    dispatch(setFilteredPredefinisani(id));
  } catch (err) {
    console.log(err);
  }
};

export const getPreglediKodSestre = (id, datum) => async dispatch => {
  try {
    let pregledi = await axios.get(`/api/pregled/zaSestru/${id}/${datum}`);
    dispatch(setPreglediKodSestre(pregledi.data));
  } catch (err) {
    console.log(err);
  }
};

export const getPreglediKodLekara = (id, datum) => async dispatch => {
  try {
    let pregledi = await axios.get(`/api/pregled/zaLekara/${id}/${datum}`);
    dispatch(setPreglediKodLekara(pregledi.data));
  } catch (err) {
    console.log(err);
  }
};

export const getPreglediKodSestreOdmor = (
  id,
  datum1,
  datum2
) => async dispatch => {
  try {
    let pregledi = await axios.get(
      `/api/pregled/zaSestruOdmor/${id}/${datum1}/${datum2}`
    );
    dispatch(setPreglediKodSestreOdmor(pregledi.data));
  } catch (err) {
    console.log(err);
  }
};

export const getPreglediKodLekaraOdmor = (
  id,
  datum1,
  datum2
) => async dispatch => {
  try {
    let pregledi = await axios.get(
      `/api/pregled/zaLekaraOdmor/${id}/${datum1}/${datum2}`
    );
    dispatch(setPreglediKodLekaraOdmor(pregledi.data));
  } catch (err) {
    console.log(err);
  }
};
