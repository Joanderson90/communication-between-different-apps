import { CommonModule, DatePipe } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ChartModule, UIChart } from 'primeng/chart';
import { repeat } from 'rxjs';
import { SensorService } from 'src/app/base/sensor/sensor.service';
import { SensorDTO, SensorType } from 'src/app/models/sensor/sensor-dto';

@Component({
  selector: 'app-sensor-control',
  templateUrl: './sensor-control.component.html',
  styleUrls: ['./sensor-control.component.scss'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, ChartModule],
  providers: [DatePipe],
})
export class SensorControlComponent {
  @ViewChild('chart') chart: UIChart | undefined;

  sensorForm: FormGroup;
  selectedItem: any = 'SOUND';
  sensorList: string[] = ['SOUND', 'ANOTHER'];
  CINCO_SEGUNDOS_EM_MILISSEGUNDO: number = 5e3;
  MAX_AMOUNT_LENGTH_ARRAY_SENSOR_DATA = 90;

  data: any;
  options: any;
  dataSensorList: SensorDTO[] = [];

  constructor(
    private _sensorService: SensorService,
    private fb: FormBuilder,
    private datePipe: DatePipe
  ) {
    this.sensorForm = this.fb.group({
      sensor: [this.sensorList[0]],
      sensitivity: [10, [Validators.min(1), Validators.max(99)]],
      isON: [false],
    });
  }

  ngOnInit(): void {
    this._sensorService
      .getData(this.sensorForm?.get('sensor')?.value)
      .pipe(repeat({ delay: this.CINCO_SEGUNDOS_EM_MILISSEGUNDO }))
      .subscribe({
        next: (data) => {
          if (data) {
            this.patchFormValue(data);
            this.updateDataSensorList(data);
            this.drawChart();
          }
        },
        error: () => console.log('Error to get sensor data.'),
      });
  }

  patchFormValue(data: SensorDTO) {
    this.sensorForm.patchValue(data);
    console.log(this.sensorForm);
  }

  updateDataSensorList(dataSensor: SensorDTO) {
    if (
      this.dataSensorList.length == this.MAX_AMOUNT_LENGTH_ARRAY_SENSOR_DATA
    ) {
      console.log('Array full');
      console.log('Cleaning data');

      this.dataSensorList = [];
    } else {
      this.dataSensorList.push(dataSensor);
    }
    console.log(this.dataSensorList);
  }

  private drawChart() {
    const documentStyle = getComputedStyle(document.documentElement);

    this.data = {
      labels: this.dataSensorList.map((data, index) => index + 1),

      datasets: [
        {
          label: 'Sensor ' + this.sensorForm?.get('sensor')?.value,
          data: this.dataSensorList.map((data) => data.data),
          fill: false,
          borderColor: documentStyle.getPropertyValue('--blue-500'),
          tension: 0.4,
        },
      ],
    };
  }

  onSelectChange() {
    console.log(this.sensorForm?.get('sensor')?.value);
  }

  getDataChart() {}

  onClickPowerSensorIcon() {
    let formInputOn = this.sensorForm.get('isON');

    if (!formInputOn?.value) {
      this.turnOnSensor(formInputOn);
    } else {
      this.turnOffSensor(formInputOn);
    }
  }

  turnOnSensor(formInputOn: any) {
    this._sensorService
      .turnOn(this.sensorForm?.get('sensor')?.value)
      .subscribe({
        next: () => {
          formInputOn?.setValue(true);
        },
        error: () => console.log(`Error to turn ON sensor.`),
      });
  }

  turnOffSensor(formInputOn: any) {
    this._sensorService
      .turnOff(this.sensorForm?.get('sensor')?.value)
      .subscribe({
        next: () => {
          formInputOn?.setValue(false);
        },
        error: () => console.log(`Error to turn OFF sensor.`),
      });
  }

  changeSensitivity() {
    console.log(this.sensorForm?.get('sensitivity')?.value);

    this._sensorService
      .setSensitivity(
        this.sensorForm?.get('sensor')?.value,
        this.sensorForm?.get('sensitivity')?.value
      )
      .subscribe({
        next: () => {},
        error: () => console.log(`Error to set sensitivity sensor.`),
      });
  }
}
