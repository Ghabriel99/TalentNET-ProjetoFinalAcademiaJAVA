import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtualizarCandidatoComponent } from './atualizar-candidato.component';

describe('AtualizarCandidatoComponent', () => {
  let component: AtualizarCandidatoComponent;
  let fixture: ComponentFixture<AtualizarCandidatoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtualizarCandidatoComponent]
    });
    fixture = TestBed.createComponent(AtualizarCandidatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
