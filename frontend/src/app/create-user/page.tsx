import { CreateUserScreen } from "@/components/create-user-screen"

export default function Home() {
  return (
    <div>
      <div className="flex justify-between mb-8">
        <div className="flex gap-3">
          <CreateUserScreen></CreateUserScreen>
        </div>
      </div>
    </div>
  )
}
