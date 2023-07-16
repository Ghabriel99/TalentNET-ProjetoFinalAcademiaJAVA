import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrlBackend from './helper';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {

  constructor(private http: HttpClient) {}

  public loginStatus = new Subject<boolean>(); //booleano para verificar se está logado ou não

  public loginUser(token: any) {
    localStorage.setItem('token', token);
  }

  // chamando o backend para gerar o token
  public generateToken(loginData: any) {
    return this.http.post(`${baseUrlBackend}/generate-token`, loginData);  //http://localhost:8080/generate-token
  }

  //verificando se o user está logado e se token está inválido
  public isLoggedIn() {
    let tokenStorage = localStorage.getItem('token');

    if (
      tokenStorage == undefined ||
      tokenStorage == '' ||
      tokenStorage == null
    ) {
      return false; // token inválido
    }
    return true;
  }

  //pegar o token do user no localStorage
  public getToken() {
    return localStorage.getItem('token');
  }

  // método para encerrar a sessão e remover o token do user
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');

    return true;
  }

  public getUser() {
    let userString = localStorage.getItem('user');

    // se a string do user não for nula, chama o JSON parse para coverter a string para o objeto js
    if (userString != null) {
      return JSON.parse(userString);
    } else {
      this.logout();
      return null;
    }
  }

  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  // role do user logado
  public getUserRole() {
    let user = this.getUser();
    return user.authorities[0].authority;
  }

  // pega o user logado
  public getCurrentUser() {
    return this.http.get(`${baseUrlBackend}/atual-usuario`);
  }
}
