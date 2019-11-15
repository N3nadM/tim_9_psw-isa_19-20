import React, {Component} from 'react'
import Button from '@material-ui/core/Button';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import axios from 'axios';

class Tabela extends Component{

    constructor(props){
        super(props)

        this.state = {
            zahtevi: []
        }
    }

    componentDidMount(){
        axios.get('http://localhost:8080/api/adminkc/zahtevi')
        .then(response => {
            this.setState({zahtevi: response.data});
        })
        .catch(error => {
            console.log(error);
        });
    }

    render(){
        const { zahtevi } = this.state;
        return(
            <div className="Tabela">
                <Table aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell><b>Ime</b></TableCell>
            <TableCell><b>Prezime</b></TableCell>
            <TableCell><b>Username</b></TableCell>
            <TableCell><b>E-mail</b></TableCell>
            <TableCell><b>Adresa</b></TableCell>
            <TableCell><b>JBZO</b></TableCell>
            <TableCell></TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
            {zahtevi.map(zahtev =>(
                <TableRow>
                     <TableCell>zahtev.getIme()</TableCell>
                     <TableCell>zahtev.getPrezime()</TableCell>
                    <TableCell>zahtev.getUsername()</TableCell>
                    <TableCell>zahtev.getEmail()</TableCell>
                    <TableCell>zahtev.getAdresa()</TableCell>
                    <TableCell>zahtev.getJbzo()</TableCell>
                    <TableCell><Button variant="contained" color="primary">Prihvati</Button></TableCell>
                    <TableCell><Button variant="contained" color="secundary">Odbij</Button></TableCell>
                </TableRow>
            ))} 
        </TableBody>
      </Table>
        </div>
        );
        
    }
}

export default Tabela;