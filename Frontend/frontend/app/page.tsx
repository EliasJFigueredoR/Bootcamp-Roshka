'use client'

import { useRouter } from "next/navigation"
import { useEffect } from "react"

export default function Page() {
  const router = useRouter()

  // Redirigir a login
  useEffect(() => {
      router.push('/login') 
  }, [router])

  return (
    <div>
    </div>
  )
}