import React, { useEffect } from "react";
import Button from "@material-ui/core/Button";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import { connect } from "react-redux";
import { getAllReceptiZaOveru, overiRecept } from "../../store/actions/recept";

const ReceptiZaOveru = ({
  korisnikId,
  receptiZaOveru,
  overenRecept,
  getAllReceptiZaOveru,
  overiRecept
}) => {
  const handleSubmit = id => {
    overenRecept = overiRecept(id);
  };

  useEffect(() => {
    getAllReceptiZaOveru(korisnikId);
    //eslint-disable-next-line
  }, []);

  {
    console.log(receptiZaOveru);
  }

  return (
    <div className="Tabela">
      <Table aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>
              <b>Naziv</b>
            </TableCell>
            <TableCell>
              <b>Sadrzaj</b>
            </TableCell>
            <TableCell>
              <b>Sifra</b>
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {receptiZaOveru &&
            !!receptiZaOveru.length &&
            receptiZaOveru.map((recept, i) => (
              <TableRow key={recept.lek.id}>
                <TableCell>{recept.lek.naziv}</TableCell>
                <TableCell>{recept.lek.sadrzaj}</TableCell>

                <TableCell>{recept.lek.sifra}</TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => handleSubmit(recept.id)}
                  >
                    Overi recept
                  </Button>
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </div>
  );
};

function mapStateToProps(state) {
  return {
    korisnikId: state.currentUser.user.id,
    receptiZaOveru: state.recept.recepti,
    overenRecept: state.recept.overenRecept
  };
}

export default connect(mapStateToProps, {
  getAllReceptiZaOveru,
  overiRecept
})(ReceptiZaOveru);
