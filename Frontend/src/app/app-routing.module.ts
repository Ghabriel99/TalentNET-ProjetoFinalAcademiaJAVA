import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { DashbordComponent } from './pages/admin/dashbord/dashbord.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { AdminGuard } from './services/admin.guard';
import { UserGuard } from './services/user.guard';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { WelcomeComponent } from './pages/admin/welcome/welcome.component';
import { ViewCandidatoComponent } from './pages/admin/view-candidato/view-candidato.component';
import { AddCandidatoComponent } from './pages/admin/add-candidato/add-candidato.component';
import { AddVagaComponent } from './pages/admin/add-vaga/add-vaga.component';
import { AtualizarCandidatoComponent } from './pages/admin/atualizar-candidato/atualizar-candidato.component';
import { ViewVagaComponent } from './pages/admin/view-vaga/view-vaga.component';
import { AtualizarVagaComponent } from './pages/admin/atualizar-vaga/atualizar-vaga.component';

const routes: Routes = [
  {
    path: '', // abre o componente de cadastrar logo ao rodar o projeto
    component: SignupComponent,
    pathMatch: 'full' // quando uma rota não é reconhecida ela é enviada para a rota '' home
  },
  {
    path: 'signup', // rota para registar
    component: SignupComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',  // rota para login
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'admin',  // rota do admin
    component: DashbordComponent,

    canActivate: [AdminGuard],  // gaurd do admin
    children: [ // componentes filhos do admin
      {
        path: 'perfil',
        component: PerfilComponent
      },
      {
        path: '',
        component: WelcomeComponent  // tela inicial do painel admin
      },
      {
        path: 'candidatos',  // rota da lista de candidatos
        component: ViewCandidatoComponent
      },
      {
        path: 'add-candidato',  // rota para adicionar os candidatos
        component: AddCandidatoComponent
      },
      {
        path: 'vagas',   //rota da lista de vagas
        component: ViewVagaComponent
      },
      {
        path: 'add-vaga',  // rota para adicionar as vagas
        component: AddVagaComponent
      },
      {
        path: 'candidato/:candidatoID',  // rota do ID do candidato para poder atualizar e remover pelo seu ID
        component: AtualizarCandidatoComponent
      },
      {
        path: 'vaga/:vagaID',  // rota do ID da vaga para poder atualizar e remover pelo seu ID
        component: AtualizarVagaComponent
      },
    ]
  },
  {
    path: 'user-dashboard',  // dashboard do usuer (implementação futura)
    component: UserDashboardComponent,
    pathMatch: 'full',
    canActivate: [UserGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
