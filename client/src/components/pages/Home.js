import React from "react";
import { connect } from "react-redux";

import AdminKlinikeProfil from "./homeSubpages/ProfilAdminKlinike";
import AdminKlinickogCentraProfil from "./homeSubpages/ProfilAKC";
import PacijentProfil from "./homeSubpages/ProjfilPacijent";

const Home = ({ currentUser }) => {
  return (
    <main>
      <p>asdasd</p>
      {currentUser.user.role[0].name === "ROLE_AK" && <AdminKlinikeProfil />}
      {currentUser.user.role[0].name === "ROLE_AKC" && (
        <AdminKlinickogCentraProfil />
      )}
      {currentUser.user.role[0].name === "ROLE_PACIJENT" && <PacijentProfil />}
    </main>
  );
};

function mapStateToProps(state) {
  return {
    currentUser: state.currentUser
  };
}

export default connect(mapStateToProps, null)(Home);
