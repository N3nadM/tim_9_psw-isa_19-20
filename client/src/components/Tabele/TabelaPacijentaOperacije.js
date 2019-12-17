import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import { connect } from "react-redux";
import { getAllOperacije } from "../../store/actions/operacija";
import Typography from "@material-ui/core/Typography";
import Divider from "@material-ui/core/Divider";
import Button from "@material-ui/core/Button";
import Tooltip from "@material-ui/core/Tooltip";
import IzvestajDialog from "../Tabs/Pacijent/IzvestajDialog";
import TableSortLabel from "@material-ui/core/TableSortLabel";
import OceniPopup from "../Tabs/Pacijent/OceniPopup";

const useStyles = makeStyles({
  root: {
    width: "100%"
  },
  tableWrapper: {
    overflow: "auto"
  }
});

const TabelaPregleda = ({ operacije, getAllOperacije, pacijentId }) => {
  const classes = useStyles();
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const [open, setOpen] = React.useState(false);
  const [text, setText] = React.useState("");
  const [orderBy, setOrderBy] = React.useState("datumPocetka");
  const [order, setOrder] = React.useState("asc");
  const [ll, setLl] = React.useState(false);

  React.useEffect(() => {
    getAllOperacije(pacijentId);
  }, []);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const handleClickOpen = izvestaj => {
    setText(izvestaj);
    setLl(false);
    setOpen(true);
  };

  const handleClose = () => {
    setText("");
    setLl(false);
    setOpen(false);
  };

  const handleLekarList = lekari => {
    setText(
      lekari.map(l => l.korisnik.ime + " " + l.korisnik.prezime).join(", ")
    );
    setLl(true);
    setOpen(true);
  };

  const sort = sviPregledi => {
    return sviPregledi.concat().sort((a, b) => {
      if (orderBy === "tip") {
        return order == "asc"
          ? a.tipPregleda.naziv - b.tipPregleda.naziv
          : b.tipPregleda.naziv - a.tipPregleda.naziv;
      } else {
        return order == "asc"
          ? new Date(a.datumPocetka) - new Date(b.datumPocetka)
          : new Date(b.datumPocetka) - new Date(a.datumPocetka);
      }
    });
  };

  const handleRequestSort = (property, event) => {
    const isDesc = orderBy === property && order === "desc";
    setOrder(isDesc ? "asc" : "desc");
    setOrderBy(property);
  };

  return (
    <>
      <Typography
        variant="h4"
        component="h2"
        style={{ textAlign: "center", marginBottom: 30 }}
      >
        Predstojece operacije
      </Typography>
      <Paper className={classes.root}>
        {operacije && (
          <>
            <div className={classes.tableWrapper}>
              <Table stickyHeader aria-label="sticky table">
                <TableHead>
                  <TableRow>
                    <TableCell align="left">Lekari</TableCell>
                    <TableCell align="right">
                      <TableSortLabel
                        active={orderBy === "tip"}
                        direction={order}
                        onClick={() => handleRequestSort("tip")}
                      >
                        Tip operacije
                      </TableSortLabel>
                    </TableCell>
                    <TableCell align="right">
                      <TableSortLabel
                        active={orderBy === "datumPocetka"}
                        direction={order}
                        onClick={() => handleRequestSort("datumPocetka")}
                      >
                        Datum Operacije
                      </TableSortLabel>
                    </TableCell>
                    <TableCell align="right">Cena</TableCell>
                    <TableCell align="right">Datum Zakazivanja</TableCell>
                    <TableCell align="right">Otkaži Operaciju</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {operacije && operacije.length > 0 ? (
                    operacije
                      .slice(
                        page * rowsPerPage,
                        page * rowsPerPage + rowsPerPage
                      )
                      .filter(p => new Date() < new Date(p.datumPocetka))
                      .map(row => {
                        return (
                          <TableRow hover tabIndex={-1} key={row.id}>
                            <TableCell align="left">
                              <Tooltip title="Pogledaj listu prisutnih lekara">
                                <Button
                                  color="primary"
                                  onClick={() => handleLekarList(row.lekari)}
                                >
                                  Lista Lekara
                                </Button>
                              </Tooltip>
                            </TableCell>
                            <TableCell align="right">
                              {row.tipPregleda.naziv}
                            </TableCell>
                            <TableCell align="right">
                              {row.datumPocetka}
                            </TableCell>
                            <TableCell align="right">
                              {row.tipPregleda.cenaOperacije}
                            </TableCell>
                            <TableCell align="right">
                              {row.datumKreiranja}
                            </TableCell>
                            <TableCell align="right">
                              <Tooltip
                                title={
                                  new Date() - new Date(row.datumPocetka) >
                                  -86400000
                                    ? "Otkazivanje nije moguce u periodu od 24 sata pre pregleda"
                                    : "Otkaži zakazani pregled"
                                }
                                leaveDelay={200}
                              >
                                <span>
                                  <Button
                                    color="secondary"
                                    variant="outlined"
                                    disabled={
                                      new Date() - new Date(row.datumPocetka) >
                                      -86400000
                                    }
                                  >
                                    Otkaži
                                  </Button>
                                </span>
                              </Tooltip>
                            </TableCell>
                          </TableRow>
                        );
                      })
                  ) : (
                    <>
                      <TableRow hover tabIndex={-1}>
                        <TableCell>Prazno</TableCell>
                        <TableCell></TableCell>
                      </TableRow>
                    </>
                  )}
                </TableBody>
              </Table>
            </div>
            <TablePagination
              rowsPerPageOptions={[10, 25, 100]}
              component="div"
              count={
                operacije
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .filter(p => new Date() < new Date(p.datumPocetka)).length
              }
              rowsPerPage={rowsPerPage}
              page={page}
              backIconButtonProps={{
                "aria-label": "previous page"
              }}
              nextIconButtonProps={{
                "aria-label": "next page"
              }}
              onChangePage={handleChangePage}
              onChangeRowsPerPage={handleChangeRowsPerPage}
            />
          </>
        )}
      </Paper>
      <Divider style={{ margin: 80 }} />
      <Typography
        variant="h4"
        component="h2"
        style={{ textAlign: "center", marginBottom: 30 }}
      >
        Istorija operacija
      </Typography>
      <Paper className={classes.root}>
        {operacije && (
          <>
            <div className={classes.tableWrapper}>
              <Table stickyHeader aria-label="sticky table">
                <TableHead>
                  <TableRow>
                    <TableCell align="left">Lekar</TableCell>
                    <TableCell align="right">
                      <TableSortLabel
                        active={orderBy === "tip"}
                        direction={order}
                        onClick={() => handleRequestSort("tip")}
                      >
                        Tip operacije
                      </TableSortLabel>
                    </TableCell>
                    <TableCell align="right">
                      <TableSortLabel
                        active={orderBy === "datumPocetka"}
                        direction={order}
                        onClick={() => handleRequestSort("datumPocetka")}
                      >
                        Datum Operacije
                      </TableSortLabel>
                    </TableCell>
                    <TableCell align="right">Cena</TableCell>
                    <TableCell align="right">Datum Zakazivanja</TableCell>
                    <TableCell align="center">Oceni</TableCell>
                    <TableCell align="right">Izveštaj</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {operacije && operacije.length > 0 ? (
                    sort(operacije)
                      .slice(
                        page * rowsPerPage,
                        page * rowsPerPage + rowsPerPage
                      )
                      .filter(p => new Date() > new Date(p.datumPocetka))
                      .map(row => {
                        return (
                          <TableRow hover tabIndex={-1} key={row.id}>
                            <TableCell align="left">
                              <Tooltip title="Pogledaj listu prisutnih lekara">
                                <Button
                                  color="primary"
                                  onClick={() => handleLekarList(row.lekari)}
                                >
                                  Lista Lekara
                                </Button>
                              </Tooltip>
                            </TableCell>
                            <TableCell align="right">
                              {row.tipPregleda.naziv}
                            </TableCell>
                            <TableCell align="right">
                              {row.datumPocetka}
                            </TableCell>
                            <TableCell align="right">
                              {row.tipPregleda.cenaOperacije}
                            </TableCell>
                            <TableCell align="right">
                              {row.datumKreiranja}
                            </TableCell>
                            <TableCell align="center">
                              <OceniPopup
                                klinikaId={row.sala.salaIdentifier.substring(
                                  0,
                                  1
                                )}
                                lekari={row.lekari}
                              />
                            </TableCell>
                            <TableCell align="right">
                              <Tooltip
                                title="Pogledaj izvestaj"
                                leaveDelay={200}
                              >
                                <span>
                                  <Button
                                    color="primary"
                                    variant="outlined"
                                    onClick={() =>
                                      handleClickOpen(row.izvestaj)
                                    }
                                  >
                                    Pogledaj
                                  </Button>
                                </span>
                              </Tooltip>
                            </TableCell>
                          </TableRow>
                        );
                      })
                  ) : (
                    <>
                      <TableRow hover tabIndex={-1}>
                        <TableCell>Prazno</TableCell>
                        <TableCell></TableCell>
                      </TableRow>
                    </>
                  )}
                </TableBody>
              </Table>
            </div>
            <TablePagination
              rowsPerPageOptions={[10, 25, 100]}
              component="div"
              count={
                operacije
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .filter(p => new Date() > new Date(p.datumPocetka)).length
              }
              rowsPerPage={rowsPerPage}
              page={page}
              backIconButtonProps={{
                "aria-label": "previous page"
              }}
              nextIconButtonProps={{
                "aria-label": "next page"
              }}
              onChangePage={handleChangePage}
              onChangeRowsPerPage={handleChangeRowsPerPage}
            />
          </>
        )}
      </Paper>
      <IzvestajDialog
        text={text}
        handleClose={handleClose}
        open={open}
        ll={ll}
      />
    </>
  );
};

const mapStateToProps = state => ({
  operacije: state.operacija.operacije,
  pacijentId: state.pacijent.pacijent.id
});

export default connect(mapStateToProps, { getAllOperacije })(TabelaPregleda);
