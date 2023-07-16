import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';

import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})

export class AdminGuard implements CanActivate {    //interface que permite que a classe seja usada como um guard(filtro) de rota.

  constructor(private loginService: LoginService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot, //rota atual
    state: RouterStateSnapshot // estados da rota
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {


    // verificando se o user está logado e possui permissão de admin
    if (
      this.loginService.isLoggedIn() &&
      this.loginService.getUserRole() == 'ADMIN'
    ) {
      return true; // ativação da rota
    }

    this.router.navigate(['login']); // se não tiver permissão, volta para tela de login
    return false;
  }
}
