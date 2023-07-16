import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { VagaService } from 'src/app/services/vaga.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-vaga',
  templateUrl: './add-vaga.component.html',
  styleUrls: ['./add-vaga.component.css']
})
export class AddVagaComponent implements OnInit{

  vaga = {
    descricao: '',
    cargo: '',
    gestor: '',
    salario: ''
  }

  constructor(
    private router: Router,
    private vagaService:VagaService,
    private snack: MatSnackBar
  ) {}


  ngOnInit(): void { }

  formVagaSubmit(form: any) {
    if (form.invalid) {
      this.snack.open('Preencha corretamente todos os campos!', 'Fechar', {
        duration: 4000
      })
      return;
    }

    this.vagaService.salvarVaga(this.vaga).subscribe(
      (dados:any) => {
        this.vaga.descricao = '',
        this.vaga.cargo = '',
        this.vaga.gestor = '',
        this.vaga.salario = '',

        console.log(dados);

        Swal.fire('Vaga salva!', 'Com sucesso no sistema', 'success')
        this.router.navigate(['/admin/vagas'])
      },
      (error) => {
        console.log(error);
        Swal.fire('Exception!', 'Erro ao salvar a vaga', 'error')
      }
    )
  }

}
