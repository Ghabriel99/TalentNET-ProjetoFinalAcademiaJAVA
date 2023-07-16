import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrlBackend from './helper';

@Injectable({
  providedIn: 'root'
})
export class VagaService {

  constructor(private http: HttpClient) { }

  public listarVagas() {
    return this.http.get(`${baseUrlBackend}/admin/vaga/lista/`) //localhost:8080/admin/vaga/lista
  }

  public salvarVaga(vaga: any) {
    return this.http.post(`${baseUrlBackend}/admin/vaga/salvar/`,vaga) //localhost:8080/admin/vaga/salvar
  }

  public removerVaga(vagaID: any) {
    return this.http.delete(`${baseUrlBackend}/admin/vaga/deletar/${vagaID}`) //localhost:8080/admin/vaga/deletar
  }

  public obterVagaID(vagaID:any) {
    return this.http.get(`${baseUrlBackend}/admin/vaga/${vagaID}`); //localhost:8080/admin/vaga/{id}
  }

  public atualizarVaga(vaga:any) {
    return this.http.put(`${baseUrlBackend}/admin/vaga/${vaga.vagaID}`, vaga); //localhost:8080/admin/vaga/{id}

  }

}
