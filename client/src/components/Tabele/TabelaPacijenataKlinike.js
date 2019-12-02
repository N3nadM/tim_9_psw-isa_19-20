import React, { useEffect, useState } from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import { makeStyles } from "@material-ui/core/styles";
import TableRow from "@material-ui/core/TableRow";
import TableSortLabel from "@material-ui/core/TableSortLabel";
import { connect } from "react-redux";
import IconButton from "@material-ui/core/IconButton";
import Tooltip from "@material-ui/core/Tooltip";
import { getPacijentiKlinike } from "../../store/actions/pacijent";
import Typography from "@material-ui/core/Typography";
import Toolbar from "@material-ui/core/Toolbar";
import FilterListIcon from "@material-ui/icons/FilterList";

import CircularProgress from "@material-ui/core/CircularProgress";

function desc(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function stableSort(array, cmp) {
  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    const order = cmp(a[0], b[0]);
    if (order !== 0) return order;
    return a[1] - b[1];
  });
  return stabilizedThis.map(el => el[0]);
}
const handleClick = event => {};
function getSorting(order, orderBy) {
  return order === "desc"
    ? (a, b) => desc(a, b, orderBy)
    : (a, b) => -desc(a, b, orderBy);
}

const headCells = [
  {
    id: "ime",
    numeric: false,
    disablePadding: true,
    label: "Ime"
  },
  { id: "prezime", numeric: false, disablePadding: false, label: "Prezime" },
  { id: "email", numeric: false, disablePadding: false, label: "E-mail" },
  { id: "telefon", numeric: false, disablePadding: false, label: "Telefon" },
  { id: "adresa", numeric: false, disablePadding: false, label: "Adresa" },
  { id: "grad", numeric: false, disablePadding: false, label: "Grad" },
  { id: "drazava", numeric: false, disablePadding: false, label: "Drzava" },
  { id: "jbzo", numeric: true, disablePadding: false, label: "JBZO" }
];

const [state, setState] = React.useState({
  ime: "",
  prezime: "",
  email: "",
  grad: "",
  jbzo: ""
});

const handleChange = e => {
  setState({ ...state, [e.target.name]: e.target.value });
};

const handleSubmit = e => {
  e.preventDefault();
};

function EnhancedTableHead(props) {
  const { classes, order, orderBy, onRequestSort } = props;
  const createSortHandler = property => event => {
    onRequestSort(event, property);
  };

  return (
    <TableHead>
      <TableRow>
        <TableCell></TableCell>
        {headCells.map((headCell, i) => (
          <TableCell
            key={headCell.id}
            align={i !== headCells.length - 1 ? "left" : "right"}
            padding={headCell.disablePadding ? "none" : "default"}
            sortDirection={orderBy === headCell.id ? order : false}
          >
            {headCell.id !== "saznaj" ? (
              <TableSortLabel
                active={orderBy === headCell.id}
                direction={order}
                onClick={createSortHandler(headCell.id)}
              >
                {headCell.label}
                {orderBy === headCell.id ? (
                  <span className={classes.visuallyHidden}>
                    {order === "desc"
                      ? "sorted descending"
                      : "sorted ascending"}
                  </span>
                ) : null}
              </TableSortLabel>
            ) : (
              headCell.label
            )}
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  );
}
const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: theme.spacing(3)
  },
  paper: {
    width: "100%",
    marginBottom: theme.spacing(2)
  },
  table: {
    minWidth: 750
  },
  tableWrapper: {
    overflowX: "auto"
  },
  visuallyHidden: {
    border: 0,
    clip: "rect(0 0 0 0)",
    height: 1,
    margin: -1,
    overflow: "hidden",
    padding: 0,
    position: "absolute",
    top: 20,
    width: 1
  },
  formControl: {
    minWidth: 120
  }
}));
const EnhancedTableToolbar = () => {
  const classes = useToolbarStyles();
  return (
    <Toolbar>
      <Typography variant="h6" id="tableTitle">
        Lista pacijenata
      </Typography>

      <Tooltip title="Filter list">
        <IconButton aria-label="filter list">
          <FilterListIcon />
        </IconButton>
      </Tooltip>
    </Toolbar>
  );
};
const useToolbarStyles = makeStyles(theme => ({
  title: {
    flex: "1 1 100%"
  }
}));

const TabelaPacijenataKlinike = ({
  korisnikId,
  pacijent: { pacijent },
  getPacijentiKlinike
}) => {
  useEffect(() => {
    getPacijentiKlinike(korisnikId);
  }, []);
  const classes = useStyles();
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("name");

  const handleRequestSort = (event, property) => {
    const isDesc = orderBy === property && order === "desc";
    setOrder(isDesc ? "asc" : "desc");
    setOrderBy(property);
  };

  return (
    <div className="TabelaPacijenataKlinike">
      <EnhancedTableToolbar />
      <div className={classes.tableWrapper}>
        <Table aria-label="simple table">
          <EnhancedTableHead
            classes={classes}
            order={order}
            orderBy={orderBy}
            onRequestSort={handleRequestSort}
          />
          <TableBody>
            {pacijent &&
              stableSort(pacijent, getSorting(order, orderBy)).map(
                (row, index) => {
                  const labelId = `enhanced-table-checkbox-${index}`;

                  return (
                    <TableRow
                      hover
                      onClick={event => handleClick(event, row.name)}
                      role="checkbox"
                      tabIndex={-1}
                      key={row.ime}
                    >
                      <TableCell></TableCell>
                      <TableCell
                        component="th"
                        id={labelId}
                        scope="row"
                        padding="none"
                      >
                        {row.ime}
                      </TableCell>
                      <TableCell align="left">{row.prezime}</TableCell>
                      <TableCell align="left">{row.email}</TableCell>
                      <TableCell align="left">{row.telefon}</TableCell>
                      <TableCell align="left">{row.adresa}</TableCell>
                      <TableCell align="left">{row.grad}</TableCell>
                      <TableCell align="left">{row.drzava}</TableCell>
                      <TableCell align="left">{row.jbzo}</TableCell>
                    </TableRow>
                  );
                }
              )}
          </TableBody>
        </Table>
      </div>
    </div>
  );
};

function mapStateToProps(state) {
  return {
    korisnikId: state.currentUser.user.id,
    pacijent: state.pacijent
  };
}

export default connect(mapStateToProps, {
  getPacijentiKlinike
})(TabelaPacijenataKlinike);
