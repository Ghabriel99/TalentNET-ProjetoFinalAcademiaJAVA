import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})

export class LoginComponent implements OnInit {
  // form de login
  formulario!: FormGroup;

  // dados username e password para realizar login e autenticação
  loginData = {
    username: '',
    password: '',
  };

  constructor(
    private loginService: LoginService,
    private snack: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit(): void {}

  formSubmit() {
    // para mostrar o alerta ao centro da tela
    const snackbarConfig = new MatSnackBarConfig();
    snackbarConfig.verticalPosition = 'top'


    //checando se o username está vazio para mostrar o alerta
     if (
      this.loginData.username.trim() == '' ||
      this.loginData.username == null
    ) {
      this.snack.open('Informe seu usuário para realizar o login!', 'Fechar', snackbarConfig);
      return;
    }

    //checando se a senha está vazia para mostrar o alerta
    if (
      this.loginData.password.trim() == '' ||
      this.loginData.password == null
    ) {
      this.snack.open('A senha é obrigatória para realizar o login!', 'Fechar', snackbarConfig);
      return;
    }

    // chamado o service de login para gerar o token para o user
    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log(data);

        this.loginService.loginUser(data.token);
        this.loginService.getCurrentUser().subscribe((user: any) => {
          this.loginService.setUser(user); //settando o token para o user
          console.log(user);

          //checando autorização de admin
          if (this.loginService.getUserRole() == 'ADMIN') {

            this.router.navigate(['admin']); //rota para admin
            this.loginService.loginStatus.next(true);

          } else if (this.loginService.getUserRole() == 'USER') {

            this.router.navigate(['user-dashboard']); //rota para user
            this.loginService.loginStatus.next(true);

          } else {
            this.loginService.logout();
          }
        });
      },
      (error) => {
        console.log(error);
        this.snack.open('Erro no login, tente novamente!', 'Fechar', {
          duration: 4000,
        });
      }
    );
  }
}
