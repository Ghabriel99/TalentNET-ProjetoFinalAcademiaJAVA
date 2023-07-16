import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCandidatoComponent } from './add-candidato.component';

describe('AddCandidatoComponent', () => {
  let component: AddCandidatoComponent;
  let fixture: ComponentFixture<AddCandidatoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddCandidatoComponent]
    });
    fixture = TestBed.createComponent(AddCandidatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
