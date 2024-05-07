export interface SensorDTO {
  data: number;
  dateGeneratedData: Date;
  sensitivity: number;
  isON: boolean;
}

export enum SensorType {
  SOUND = 'SOUND',
}
