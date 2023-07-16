import { Injectable } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { LoginService } from './login.service';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor (private loginService: LoginService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let authRequest = req;
    const token = this.loginService.getToken();

    if (token != null) {
      authRequest = authRequest.clone({ // clonando a cabeçalho da requisição
        setHeaders: {Authorization: `Bearer ${token}`} //modificando e clonando a peticao do token para o padrao Bearer
      })
    }
    return next.handle(authRequest); // próximo filtro da requisicão
  }
}

export const authInterceptorProviders = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true  //muitos HTTP INTERCEPTORS para muitas requisições

  }
]
