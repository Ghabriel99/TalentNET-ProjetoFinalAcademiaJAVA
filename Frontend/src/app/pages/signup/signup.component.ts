import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{

  public user = {
    username: '',
    password: '',
    nome: '',
    email: '',
    telefone: ''
  }

  constructor(private userService: UserService, private snack: MatSnackBar) {

  }

  ngOnInit(): void { }

  formSubmit() {
    console.log(this.user);

    if (this.user.username == '' || this.user.username == null && this.user.password == '' || this.user.password == null) {
      this.snack.open('O nome e senha do usuário é obrigatório cadastrar!', 'Aceitar', {
        duration: 5000,
        verticalPosition: 'top',
        horizontalPosition: 'left'

      });
      return;
    }


    this.userService.registrarUsuario(this.user).subscribe(
      (dados) => {
        console.log(dados);
        Swal.fire('Usuário salvo', 'Profissional salvo na base de dados!', 'success');
      }, (error) => {
        console.log(error);
        this.snack.open("Exception no sistema!", 'Aceitar', {
          duration: 3000,
        });
      }
    )
  }

}
