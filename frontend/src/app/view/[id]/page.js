'use client';

import { useParams } from 'next/navigation';
import ViewBenchmark from '../../components/ViewBenchmark';

export default function ViewPage() {
    const params = useParams();
    return <ViewBenchmark id={params.id} />;
}