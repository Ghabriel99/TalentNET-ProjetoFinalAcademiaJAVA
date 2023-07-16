import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrlBackend from './helper';

@Injectable({
  providedIn: 'root'
})

export class CandidatoServiceService {

  constructor(private http: HttpClient) { }

  public listarCandidatos() {
    return this.http.get(`${baseUrlBackend}/admin/candidato/lista/`);   //localhost:8080/admin/candidato/lista
  }

  public salvarCandidato(candidato: any) {
    return this.http.post(`${baseUrlBackend}/admin/candidato/salvarCandidato/`,candidato);   //localhost:8080/admin/candidato/salvarCandidato/
  }

  public removerCandidato(candidatoID: any) {
    return this.http.delete(`${baseUrlBackend}/admin/candidato/deletar/${candidatoID}`);   //localhost:8080/admin/candidato/deletar
  }

  public obterCandidatoID(candidatoID: any) {
    return this.http.get(`${baseUrlBackend}/admin/candidato/${candidatoID}`); //localhost:8080/admin/candidato/{id}
  }

  public atualizarCandidato(candidato: any) {
    return this.http.put(`${baseUrlBackend}/admin/candidato/${candidato.candidatoID}`, candidato); //localhost:8080/admin/candidato/{id}
  }
}
