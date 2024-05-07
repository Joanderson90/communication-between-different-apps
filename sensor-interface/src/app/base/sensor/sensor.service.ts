import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SensorDTO, SensorType } from 'src/app/models/sensor/sensor-dto';
import { environment } from 'src/assets/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SensorService {
  private apiUrl: string;

  protected constructor(protected http: HttpClient) {
    this.apiUrl = environment.apiUrl + 'sensor';
  }

  getData(sensor: string): Observable<SensorDTO> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('sensorType', sensor);

    const options = { headers: this.getHeaders(), params: httpParams };
    return this.http.get<SensorDTO>(this.apiUrl + '/getSensorData', options);
  }

  isOn(sensor: string): Observable<boolean> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('sensorType', sensor);

    const options = { headers: this.getHeaders(), params: httpParams };
    return this.http.get<boolean>(this.apiUrl + '/isON', options);
  }

  turnOn(sensor: string) {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('sensorType', sensor);

    const options = { headers: this.getHeaders(), params: httpParams };
    return this.http.post(this.apiUrl + '/turnON', null, options);
  }

  turnOff(sensor: string) {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('sensorType', sensor);

    const options = { headers: this.getHeaders(), params: httpParams };
    return this.http.post(this.apiUrl + '/turnOFF', null, options);
  }

  setSensitivity(sensor: string, sensitivity: number) {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('sensorType', sensor);
    httpParams = httpParams.append('sensitivity', sensitivity);

    const options = { headers: this.getHeaders(), params: httpParams };
    return this.http.post(this.apiUrl + '/setSensitivity', null, options);
  }

  public getHeaders(): HttpHeaders {
    let customHeader = new HttpHeaders().set(
      'Content-Type',
      'application/json'
    );

    return customHeader;
  }
}
