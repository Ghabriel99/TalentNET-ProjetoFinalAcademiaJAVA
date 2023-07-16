import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewVagaComponent } from './view-vaga.component';

describe('ViewVagaComponent', () => {
  let component: ViewVagaComponent;
  let fixture: ComponentFixture<ViewVagaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewVagaComponent]
    });
    fixture = TestBed.createComponent(ViewVagaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
