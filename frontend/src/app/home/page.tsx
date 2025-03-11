import {
  Card,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { MoneyReceiveSquareIcon, MoneySendSquareIcon } from "hugeicons-react"
import { DialogCreateRecord } from "@/components/dialog-create-record"
import { TableRecords } from "@/components/table-records"
import { auth } from '@/auth'


export default async function Home() {
  const session = await auth();

  return (
    <div>
      <div className="flex justify-between mb-8">
        <div className="flex gap-3">
          <div>
            <p>Bem-vindo, {session?.user?.name}!</p>
          </div>
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
