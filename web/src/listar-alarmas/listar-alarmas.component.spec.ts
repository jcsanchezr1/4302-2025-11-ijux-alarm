import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarAlarmasComponent } from './listar-alarmas.component';

describe('ListarAlarmasComponent', () => {
  let component: ListarAlarmasComponent;
  let fixture: ComponentFixture<ListarAlarmasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarAlarmasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarAlarmasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
