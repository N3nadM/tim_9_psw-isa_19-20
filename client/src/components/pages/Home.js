import React from "react";
import { connect } from "react-redux";

import AdminKlinikeProfil from "./homeSubpages/ProfilAdminKlinike";
import AdminKlinickogCentraProfil from "./homeSubpages/ProfilAKC";

const Home = ({ currentUser }) => {
  return (
    <main>
      {console.log(currentUser.user)}
      {currentUser.user.role[0].name === "ROLE_AK" && <AdminKlinikeProfil />}
      {currentUser.user.role[0].name === "ROLE_AKC" && (
        <AdminKlinickogCentraProfil />
      )}
    </main>
  );
};

function mapStateToProps(state) {
  return {
    currentUser: state.currentUser
  };
}

export default connect(mapStateToProps, null)(Home);
