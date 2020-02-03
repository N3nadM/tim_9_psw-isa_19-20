//import Geolocation from "@react-native-community/geolocation";
import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Paper } from "@material-ui/core";
import Map from "pigeon-maps";
import Marker from "pigeon-marker";

let poz = "";

/*function nadjiLokaciju() {
  Geolocation.getCurrentPosition(
    position => {
      console.log(position);
    },
    error => {
      // See error code charts below.
      console.log(error.code, error.message);
    },
    { enableHighAccuracy: true, timeout: 15000, maximumAge: 10000 }
  );
}*/

const Mapa = ({}) => {
  //nadjiLokaciju();
  return (
    <Paper style={{ padding: 50, paddingBottom: 75 }}>
      <Map center={[45.2393885, 19.887316]} zoom={12} height={400}>
        <Marker
          anchor={[45.2393885, 19.887316]}
          payload={1}
          onClick={({ event, anchor, payload }) => {}}
        />
      </Map>
    </Paper>
  );
};

function mapStateToProps(state) {
  return {};
}

export default connect(mapStateToProps, {})(Mapa);
