export interface OrderDetailsModel {
  id?: number
  petId: number,
  quantity: number,
  shipDate: Date,
  status: string,
  complete: boolean
}
