import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SensorControlComponent } from './sensor-control.component';

describe('SensorControlComponent', () => {
  let component: SensorControlComponent;
  let fixture: ComponentFixture<SensorControlComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SensorControlComponent]
    });
    fixture = TestBed.createComponent(SensorControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
