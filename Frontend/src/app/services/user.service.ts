import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import baseUrlBackend from './helper';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public registrarUsuario(user:any) {
    return this.http.post(`${baseUrlBackend}/usuarios/`,user)
  }
}
