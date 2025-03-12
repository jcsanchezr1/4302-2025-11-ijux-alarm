import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecuperarContrasenaNuevaComponent } from './recuperar-contrasena-nueva.component';

describe('RecuperarContrasenaNuevaComponent', () => {
  let component: RecuperarContrasenaNuevaComponent;
  let fixture: ComponentFixture<RecuperarContrasenaNuevaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecuperarContrasenaNuevaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecuperarContrasenaNuevaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
