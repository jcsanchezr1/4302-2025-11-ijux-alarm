import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HabilitarDeshabilitarComponent } from './habilitar-deshabilitar.component';

describe('HabilitarDeshabilitarComponent', () => {
  let component: HabilitarDeshabilitarComponent;
  let fixture: ComponentFixture<HabilitarDeshabilitarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HabilitarDeshabilitarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HabilitarDeshabilitarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
