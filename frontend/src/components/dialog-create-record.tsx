import { Button } from "@/components/ui/button"
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import { ButtonCreateRecord } from '@/components/button-create-record'
import { Tabs, TabsContent, TabsList, TabsTrigger } from "./ui/tabs"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "./ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"


export function DialogCreateRecord() {
  return (
    <Dialog>
      <DialogTrigger asChild>
        <ButtonCreateRecord />
      </DialogTrigger>
      <DialogContent className="sm:max-w-[525px]">
        <DialogHeader>
          <DialogTitle>Edit profile</DialogTitle>
          <DialogDescription>
            Make changes to your profile here. Click save when you're done.
          </DialogDescription>
        </DialogHeader>
        <Tabs defaultValue="account" className="grid gap-4 py-4">
            <TabsList className="grid w-full grid-cols-2">
                <TabsTrigger value="account" className="text-teal-300">
                    <p className="text-teal-500">Income</p>
                </TabsTrigger>
                <TabsTrigger value="password">
                    <p className="text-rose-500">Expense</p>
                </TabsTrigger>
            </TabsList>
            <TabsContent value="account">
                <div className="space-y-1 flex items-center gap-3">
                    <Label htmlFor="value">R$</Label>
                    <Input id="value" type="real" placeholder="10.00" />
                </div>
            </TabsContent>
            <TabsContent value="password">
            <div className="space-y-1 flex items-center gap-3">
                    <Label htmlFor="value">R$</Label>
                    <Input id="value" type="real" placeholder="10.00" />
                </div>
            </TabsContent>
        </Tabs>

        <Button>Create</Button>
      </DialogContent>
    </Dialog>
  )
}
