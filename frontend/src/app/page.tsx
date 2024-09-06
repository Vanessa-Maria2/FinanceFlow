import {
  Card,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { MoneyReceiveSquareIcon, MoneySendSquareIcon, AddCircleHalfDotIcon } from "hugeicons-react"
import { Button } from "@/components/ui/button"
import { DialogCreateRecord } from "@/components/dialog-create-record"
import { TableRecords } from "@/components/table-records"


export default function Home() {
  return (
    <div>
      <div className="flex justify-between mb-8">
        <div className="flex gap-3">
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <MoneyReceiveSquareIcon className="text-teal-500 " />
                R$ 500
              </CardTitle>
            </CardHeader>
          </Card>
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <MoneySendSquareIcon className="text-rose-500 " />
                R$ 700
              </CardTitle>
            </CardHeader>
          </Card>
        </div>

        <div>
          <DialogCreateRecord />
        </div>
      </div>


      <TableRecords />

    </div>
  )
}
